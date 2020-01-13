/*******************************************************************************
 * Copyright (c) 2013, 2020 GoPivotal, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     GoPivotal, Inc. - initial API and implementation
 *******************************************************************************/
package org.springframework.ide.eclipse.boot.wizard.starters;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.springsource.ide.eclipse.commons.frameworks.ui.internal.utils.ProjectFilter;
import org.springsource.ide.eclipse.commons.frameworks.ui.internal.utils.SelectionUtils;
import org.springsource.ide.eclipse.commons.livexp.util.ExceptionUtil;

public class AddStartersHandler extends AbstractHandler {

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			ISelection selection = HandlerUtil.getCurrentSelection(event);
			if (selection!=null) {
				List<IProject> projects = SelectionUtils.getProjects(selection, ProjectFilter.anyProject);
				if (projects!=null && !projects.isEmpty()) {
					IProject project = projects.get(0);
					AddStartersDialog.openFor(project, HandlerUtil.getActiveShell(event));
				}
			}
		} catch (Throwable e) {
			org.springsource.ide.eclipse.commons.livexp.util.Log.log(e);
			MessageDialog.openError(HandlerUtil.getActiveShell(event), "Error", ExceptionUtil.getMessage(e));
		}
		return null;
	}

}
