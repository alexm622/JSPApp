package com.alex.forums.threads;

import java.util.Date;

public class Thread {
	
	public Thread(long id, String name, String creator,
			long creatorID, Date creationDate, long postCount, boolean isDeleted,
			boolean isArchived, boolean isLocked ) {
		
		this.id = id;
		this.name = name;
		this.creator = creator;
		this.creatorID = creatorID;
		this.creationDate = creationDate;
		this.postcount = postCount;
		this.isDeleted = isDeleted;
		this.isArchived = isArchived;
		this.isLocked = isLocked;
		
	}
	
	public final long id;
	public final String name;
	public final String creator;
	public final long creatorID;
	public final Date creationDate;
	public final long postcount;
	public final boolean isDeleted;
	public final boolean isArchived;
	public final boolean isLocked;
}
