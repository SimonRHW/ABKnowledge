package com.simon.asm.plugin

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.simon.asm.core.asm.AsmClassVisitor
import groovy.io.FileType
import org.apache.commons.io.FileUtils
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter

public class LifeCycleTransform extends Transform {

    @Override
    String getName() {
        return "LifeCycleTransform"
    }

    /**
     * CONTENT_CLASS：编译后的字节码文件（jar 包或目录）
     * CONTENT_RESOURCES：标准的 Java 资源
     *
     * @return
     */
    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    /**
     * 作用范围：
     * 1. PROJECT：只处理当前项目
     * 2. SUB_PROJECTS：只处理子项目
     * 3. PROJECT_LOCAL_DEPS：只处理当前项目的本地依赖,例如 jar, aar
     * 4. EXTERNAL_LIBRARIES：只处理外部的依赖库
     * 5. PROVIDED_ONLY：只处理本地或远程以 provided 形式引入的依赖库
     * 6. TESTED_CODE：只处理测试代码
     *
     * @return 返回处理的scopes
     */
    @Override
    Set<QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    /**
     * 是否支持增量更新
     *
     * @return
     */
    @Override
    boolean isIncremental() {
        return false
    }

/**
 * inputs：inputs 中是传过来的输入流，其中有两种格式，一种是 jar 包格式，一种是 directory（目录格式）。
 * outputProvider：outputProvider 获取到输出目录，最后将修改的文件复制到输出目录，这一步必须做，否则编译会报错
 * @param transformInvocation 可以获取到两个数据的流向
 * @throws TransformException* @throws InterruptedException* @throws IOException
 */
    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        //拿到所有的class文件
        Collection<TransformInput> transformInputs = transformInvocation.inputs
        TransformOutputProvider outputProvider = transformInvocation.outputProvider
        if (outputProvider != null) {
            outputProvider.deleteAll()
        }

        transformInputs.each { TransformInput transformInput ->

            transformInput.jarInputs.each {
                JarInput jarInput ->
                    File file = jarInput.file
                    System.out.println("find jar input: " + file.name)
                    //处理完输入文件后把输出传给下一个文件
                    def dest = outputProvider.getContentLocation(jarInput.name, jarInput.contentTypes,
                            jarInput.scopes, Format.JAR)
                    FileUtils.copyFile(file, dest)
            }
            // 遍历directoryInputs(文件夹中的class文件) directoryInputs代表着以源码方式参与项目编译的所有目录结构及其目录下的源码文件
            // 比如我们手写的类以及R.class、BuildConfig.class以及MainActivity.class等
            transformInput.directoryInputs.each { DirectoryInput directoryInput ->
                File dir = directoryInput.file
                if (dir) {
                    dir.traverse(type: FileType.FILES, nameFilter: ~/.*\.class/) { File file ->
                        System.out.println("find class: " + file.name)
                        //对class文件进行读取与解析，负责解析 .class 文件中的字节码，并将所有字节码传递给 ClassWriter。
                        ClassReader classReader = new ClassReader(file.bytes)
                        //对class文件的写入，继承自 ClassVisitor，它是生成字节码的工具类，负责将修改后的字节码输出为 byte 数组。
                        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
                        //访问class文件相应的内容，解析到某一个结构就会通知到ClassVisitor的相应方法
                        ClassVisitor classVisitor = new AsmClassVisitor(classWriter)
                        //依次调用 ClassVisitor接口的各个方法
                        classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES)
                        //toByteArray方法会将最终修改的字节码以 byte 数组形式返回。
                        byte[] bytes = classWriter.toByteArray()

                        //通过文件流写入方式覆盖掉原先的内容，实现class文件的改写。
                        //FileOutputStream outputStream = new FileOutputStream( file.parentFile.absolutePath + File.separator + fileName)
                        FileOutputStream outputStream = new FileOutputStream(file.path)
                        outputStream.write(bytes)
                        outputStream.close()
                    }
                }

                //处理完输入文件后把输出传给下一个文件
                def dest = outputProvider.getContentLocation(directoryInput.name, directoryInput.contentTypes,
                        directoryInput.scopes, Format.DIRECTORY)
                FileUtils.copyDirectory(directoryInput.file, dest)
            }
        }
    }

}