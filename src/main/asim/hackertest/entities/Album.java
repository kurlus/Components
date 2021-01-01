package asim.hackertest.entities;

import java.sql.Date;

/**
 *  - class mapped by in memory database entity Album 
 *  - class define associations with Artist and Song entities as follows :-
 *    a) Many to Many [ Artist-Album association ]. New entity Artist_Albums
 *       created in memory database {@link #/resources/schema.sql}
 *    b) One to Many [ Album-Song aggregation ] defined supported by annotations     
 *  - class variables mapped to Album columns by annotations        
 *  - overloaded constructors defined and can be tuned in future enhancements 
 */
public class Album 
{
	private String name;
	private Long id = null;
	private Date created = null;
	private Date lastmodified = null;
	private Integer yearReleased = null;

	public Album()
	{
	}

	public Album(Long id, String name, Integer yearReleased, Date created, Date lastmodified)
	{
		this.id = id;
		this.name = name;
		this.yearReleased = yearReleased;
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

	public Integer getYearReleased() {
		return yearReleased;
	}

	public void setYearReleased(Integer yearReleased) {
		this.yearReleased = yearReleased;
	}
	
	@Override
	public String toString() {
		return "Album [name=" + name + ", id=" + id + ", created=" + created + ", lastmodified=" + lastmodified
				+ ", yearReleased=" + yearReleased + "]";
	}

}
