/*
 * IntelliJ IDEA Jalopy Formatter plugin
 * Copyright (C) 2012  Alexey Hanin <mail@alexeyhanin.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it undeon) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.alexeyhanin.intellij.jalopyplugin.swing;

import com.alexeyhanin.intellij.jalopyplugin.model.JalopySettingsModel;
import com.alexeyhanin.intellij.jalopyplugin.util.Provider;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBPanel;
import org.jdesktop.swingx.HorizontalLayout;

import javax.swing.AbstractButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JalopySettingsForm extends JBPanel implements ChangeListener {

    private final Provider<JalopySettingsModel> jalopySettingsModelProvider;

    private JBCheckBox formatOnSaveCheckBox;

    private boolean isDirty;

    public JalopySettingsForm(final Provider<JalopySettingsModel> jalopySettingsModelProvider) {
        super(new HorizontalLayout());
        this.jalopySettingsModelProvider = jalopySettingsModelProvider;
        initUI();
    }

    private void initUI() {
        final JBPanel panel = new JBPanel();

        formatOnSaveCheckBox = addDirtyChangeListener(new JBCheckBox("Reformat on file save"));
        panel.add(formatOnSaveCheckBox);

        applyModelState();

        add(panel);
    }

    private <T extends AbstractButton> T addDirtyChangeListener(final T button) {
        button.addChangeListener(this);
        return button;
    }

    private void applyModelState() {
        formatOnSaveCheckBox.setSelected(jalopySettingsModelProvider.get().isFormatOnSaveEnabled());
    }

    public void flushChanges() {
        jalopySettingsModelProvider.get().setFormatOnSaveEnabled(formatOnSaveCheckBox.isSelected());
    }

    public boolean isDirty() {
        return isDirty;
    }

    @Override
    public void stateChanged(final ChangeEvent e) {
        this.isDirty = true;
    }

    public void reset() {
        applyModelState();
        this.isDirty = false;
    }
}