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

package com.alexeyhanin.intellij.jalopyplugin.util;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.vfs.VirtualFile;
import de.hunsicker.jalopy.Jalopy;

import java.io.StringReader;
import java.io.StringWriter;

public class JalopyDocumentFormatter {

    public static boolean format(final Document document) {
        final VirtualFile virtualFile = FileDocumentManager.getInstance().getFile(document);

        if (virtualFile != null) {

            final String text = document.getText();
            final StringWriter writer = new StringWriter(text.length());

            final Jalopy jalopy = new Jalopy();
            jalopy.setInput(new StringReader(text), virtualFile.getPath());
            jalopy.setOutput(writer);

            if (jalopy.format()) {
                final String formattedText = writer.getBuffer().toString();
                document.setText(formattedText);
                return true;
            }
        }

        return false;
    }
}