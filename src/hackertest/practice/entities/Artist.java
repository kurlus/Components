package hackertest.practice.entities;

import java.sql.Date;

/**
 *  - class mapped by in memory database entity Artist 
 *  - class define associations with Album entity as follows :-
 *    a) Many to Many [ Artist-Album association ]. New entity Artist_Albums
 *       created in memory database {@link #/resources/schema.sql} *        
 *  - class variables mapped to Artist columns by annotations        
 *  - overloaded constructors defined and can be tuned in future enhancements 
 */

public class Artist 
{

	private String name;
	private Long id = null;
	private Date created = null;
	private Date lastmodified = null;

	public Artist()
	{
	}

	public Artist(Long id, String name, Date created, Date lastmodified)
	{
		this.id = id;
		this.name = name;
		this.created = created;
		this.lastmodified = lastmodified;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastmodified() {
		return lastmodified;
	}

	public void setLastmodified(Date lastmodified) {
		this.lastmodified = lastmodified;
	}
	
	@Override
	public String toString() {
		return "Artist [name=" + name + ", id=" + id + ", created=" + created + ", lastmodified=" + lastmodified + "]";
	}

}

