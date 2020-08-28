package com.simon.asm.core.plugin;

import com.android.build.api.transform.DirectoryInput;
import com.android.build.api.transform.Format;
import com.android.build.api.transform.JarInput;
import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformInvocation;
import com.android.build.api.transform.TransformOutputProvider;
import com.android.build.gradle.internal.pipeline.TransformManager;
import com.simon.asm.core.asm.AsmClassVisitor;

import org.apache.commons.io.FileUtils;
import org.gradle.api.Project;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

/**
 * @author Simon
 * @date 2020/8/24
 * @desc
 */
public class AsmTransform extends Transform {

    Project project;

    public AsmTransform(Project project) {
        System.out.println("===========AsmTransform  init  ============");
        this.project = project;
    }

    /**
     * @return 返回transform的name
     */
    @Override
    public String getName() {
        return AsmTransform.class.getName();
    }

    /**
     * CONTENT_CLASS：编译后的字节码文件（jar 包或目录）
     * CONTENT_RESOURCES：标准的 Java 资源
     *
     * @return
     */
    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
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
    public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    /**
     * 是否支持增量更新
     *
     * @return
     */
    @Override
    public boolean isIncremental() {
        return false;
    }

    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation);
        //消费型输入，可以从中获取jar包和class文件夹路径,即拿到所有的class文件。需要输出给下一个任务
        Collection<TransformInput> inputs = transformInvocation.getInputs();

        //OutputProvider管理输出路径，如果消费型输入为空，你会发现OutputProvider == null
        TransformOutputProvider outputProvider = transformInvocation.getOutputProvider();
        if (outputProvider != null) {
            outputProvider.deleteAll();
        }
        for (TransformInput input : inputs) {
            for (JarInput jarInput : input.getJarInputs()) {
                if (outputProvider != null) {
                    File dest = outputProvider.getContentLocation(
                            jarInput.getFile().getAbsolutePath(),
                            jarInput.getContentTypes(),
                            jarInput.getScopes(),
                            Format.JAR);
                    //将修改过的字节码copy到dest，就可以实现编译期间干预字节码的目的了
                    FileUtils.copyFile(jarInput.getFile(), dest);
                }
            }
            for (DirectoryInput directoryInput : input.getDirectoryInputs()) {
                if (outputProvider != null) {
                    File dest = outputProvider.getContentLocation(directoryInput.getName(),
                            directoryInput.getContentTypes(), directoryInput.getScopes(),
                            Format.DIRECTORY);
                    //将修改过的字节码copy到dest，就可以实现编译期间干预字节码的目的了
                    //FileUtils.copyDirectory(directoryInput.getFile(), dest)
                    transformDir(directoryInput.getFile(), dest);
                }
            }
        }

    }

    private static void transformDir(File input, File dest) throws IOException {
        if (dest.exists()) {
            FileUtils.forceDelete(dest);
        }
        FileUtils.forceMkdir(dest);
        String srcDirPath = input.getAbsolutePath();
        String destDirPath = dest.getAbsolutePath();
        for (File file : Objects.requireNonNull(input.listFiles())) {
            String destFilePath = file.getAbsolutePath().replace(srcDirPath, destDirPath);
            File destFile = new File(destFilePath);
            if (file.isDirectory()) {
                transformDir(file, destFile);
            } else if (file.isFile()) {
                FileUtils.touch(destFile);
                weave(file.getAbsolutePath(), destFile.getAbsolutePath());
            }
        }
    }

    private static void weave(String inputPath, String outputPath) {
        try {
            FileInputStream is = new FileInputStream(inputPath);
            //对class文件进行读取与解析
            ClassReader cr = new ClassReader(is);
            //对class文件的写入
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            //访问class文件相应的内容，解析到某一个结构就会通知到ClassVisitor的相应方法
            AsmClassVisitor adapter = new AsmClassVisitor(cw);
            //依次调用 ClassVisitor接口的各个方法
            cr.accept(adapter, ClassReader.EXPAND_FRAMES);
            //toByteArray方法会将最终修改的字节码以 byte 数组形式返回。
            byte[] bytes = cw.toByteArray();
            //通过文件流写入方式覆盖掉原先的内容，实现class文件的改写。
            FileOutputStream fos = new FileOutputStream(outputPath);
            fos.write(bytes);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
