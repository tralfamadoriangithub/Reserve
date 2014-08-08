package com.epam.task6.tag;

import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import com.epam.task6.tableentity.WorkerTableEntity;

public class FreeWorkersTableTag extends TagSupport{

	private List<WorkerTableEntity> workers;
}
