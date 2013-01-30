package com.fabyle.team.managing.Services.imp;

import java.util.Comparator;

import org.jfree.data.gantt.Task;

public class ComparateurTask implements Comparator<Task> {

	@Override
	public int compare(Task o1, Task o2) {
		
		if (o1.getDuration().getStart().before(o2.getDuration().getStart())){
			return -1;
		}
		if (o1.getDuration().getStart().after(o2.getDuration().getStart())){
			return +1;
		}
		// TODO Auto-generated method stub
		return 0;
	}

}
