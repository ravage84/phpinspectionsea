package com.kalessil.phpStorm.phpInspectionsEA.gui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.kalessil.phpStorm.phpInspectionsEA.EAUltimateSettings;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.components.AbstractProjectComponent;

import javax.swing.*;

/*
 * This file is part of the Php Inspections (EA Extended) package.
 *
 * (c) Vladimir Reznichenko <kalessil@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

public class EAUltimateSidebarComponent extends AbstractProjectComponent {
    final private String TOOL_WINDOW_ID = "PHP Inspections";
    final private ToolWindowManager windowManager;

    protected EAUltimateSidebarComponent(@NotNull Project project) {
        super(project);
        this.windowManager = ToolWindowManager.getInstance(project);
    }

    @Override
    public void projectOpened() {
        if (!this.isInstantiated()) {
            final ToolWindow window = this.windowManager.registerToolWindow(TOOL_WINDOW_ID, this.buildPanel(), ToolWindowAnchor.RIGHT);
            window.setIcon(new ImageIcon(this.getClass().getResource("/logo_15x15.png")));
            window.setTitle("project settings");
        }
    }

    @NotNull
    private JPanel buildPanel() {
        final EAUltimateSettings settings = EAUltimateSettings.getInstance();

        return OptionsComponent.create(component -> {
            component.addPanel("License status",              panel -> {});
            component.addPanel("Distraction level settings",  panel ->
                    panel.addCheckbox("Analyze only modified files", settings.getCheckOnlyChangedFiles(), settings::setCheckOnlyChangedFiles)
            );
            component.addPanel("Active code analysis groups",   panel -> {
                panel.addCheckbox("Architecture",             true, (isSelected) -> {});
                panel.addCheckbox("Control flow",             true, (isSelected) -> {});
                panel.addCheckbox("Code style",               true, (isSelected) -> {});
                panel.addCheckbox("Language level migration", true, (isSelected) -> {});
                panel.addCheckbox("Performance",              true, (isSelected) -> {});
                panel.addCheckbox("Probable bugs",            true, (isSelected) -> {});
                panel.addCheckbox("Security",                 true, (isSelected) -> {});
                panel.addCheckbox("Unused",                   true, (isSelected) -> {});
            });
        });
    }

    @Override
    public void projectClosed() {
        if (this.isInstantiated()) {
            this.windowManager.unregisterToolWindow(TOOL_WINDOW_ID);
        }
    }

    private boolean isInstantiated() {
        return this.windowManager.getToolWindow(TOOL_WINDOW_ID) != null;
    }
}
