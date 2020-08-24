package com.simon.asm.core.plugin;

import com.android.build.gradle.AppExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * @author Simon
 * @date 2020/8/24
 * @desc
 */
public class AsmPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        System.out.println("=========== asm-plugin  start  ============");
        AppExtension appExtension = project.getExtensions().findByType(AppExtension.class);
        assert appExtension != null;
        appExtension.registerTransform(new AsmTransform(project));
    }
}
