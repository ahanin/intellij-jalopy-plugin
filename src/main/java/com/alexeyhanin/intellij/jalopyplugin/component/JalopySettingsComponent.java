/*
 * IntelliJ IDEA Jalopy Formatter plugin
 * Copyright (C) 2012  Alexey Hanin <mail@alexeyhanin.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.alexeyhanin.intellij.jalopyplugin.component;

import com.alexeyhanin.intellij.jalopyplugin.model.JalopySettingsModel;
import com.alexeyhanin.intellij.jalopyplugin.swing.JalopySettingsForm;
import com.alexeyhanin.intellij.jalopyplugin.util.Provider;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;

@State(
        name = "JalopyPlugin.Settings",
        storages = {
                @Storage(id = "default", file = StoragePathMacros.APP_CONFIG + "/jalopy-formatter.xml")
        }
)
public class JalopySettingsComponent implements Configurable, PersistentStateComponent<JalopySettingsModel>,
        Provider<JalopySettingsModel>, ApplicationComponent {

    private JalopySettingsModel jalopySettingsModel = new JalopySettingsModel();
    private JalopySettingsForm jalopySettingsForm;

    @Nls
    @Override
    public String getDisplayName() {
        return "Jalopy";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        jalopySettingsForm = new JalopySettingsForm(this);
        return jalopySettingsForm;
    }

    @Override
    public boolean isModified() {
        return jalopySettingsForm.isDirty();
    }

    @Override
    public void apply() throws ConfigurationException {
        jalopySettingsForm.flushChanges();
        jalopySettingsForm.reset();
    }

    @Override
    public void reset() {
        jalopySettingsForm.reset();
    }

    @Override
    public void disposeUIResources() {
    }

    @Nullable
    @Override
    public JalopySettingsModel getState() {
        return jalopySettingsModel;
    }

    @Override
    public void loadState(final JalopySettingsModel jalopySettingsModel) {
        this.jalopySettingsModel = jalopySettingsModel;
    }

    @Override
    public JalopySettingsModel get() {
        return jalopySettingsModel;
    }

    @Override
    public void initComponent() {
    }

    @Override
    public void disposeComponent() {
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "JalopyPlugin." + getClass().getSimpleName();
    }
}
