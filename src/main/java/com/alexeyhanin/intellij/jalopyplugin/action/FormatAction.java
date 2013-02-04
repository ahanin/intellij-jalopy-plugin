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
package com.alexeyhanin.intellij.jalopyplugin.action;

import com.alexeyhanin.intellij.jalopyplugin.util.JalopyDocumentFormatter;
import static com.alexeyhanin.intellij.jalopyplugin.util.RuntimeHelper.newWriteAction;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Editor;
public class FormatAction extends AnAction {


    public void actionPerformed(final AnActionEvent event) {

        final Editor editor = event.getData(PlatformDataKeys.EDITOR);

        final Runnable updateAction = newWriteAction(new EditorDocumentUpdateAction(editor));
        CommandProcessor.getInstance()
        .executeCommand(event.getProject(), updateAction, "jalopy.format", null);
    }

    private static class EditorDocumentUpdateAction implements Runnable {

        private final Editor editor;

        public EditorDocumentUpdateAction(final Editor editor) {
            this.editor = editor;
        }

        @Override
        public void run() {

            if (editor != null) {
                JalopyDocumentFormatter.format(editor.getDocument());
            }
        }
    }
}
