package hackertest.practice.entities;

import java.sql.Date;
/**
 *  - class -> Java Bean mapped by in memory database entity Song 
 *  - class define associations with Album entity as follows :-
 *     - One to Many [ Album-Song aggregation ] definition 
 *  - class variables mapped to entity Song columns by annotations        
 *  - overloaded constructors defined and can be tuned in future enhancements 
 */
public class Song 
{
	private Long id = null;
	private Integer track;
	private Long albumId = null;
	private String name;
	private Date created = null;
	private Date lastmodified = null;

	public Song()
	{
	}

	public Song(Long id, Integer track, String name, Date created, Date lastmodified)
	{
		this.id = id;
		this.track = track;
		this.name = name;		
		this.created = created;
		this.lastmodified = lastmodified;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTrack() {
		return track;
	}

	public void setTrack(Integer track) {
		this.track = track;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", track=" + track + ", albumId=" + albumId + ", name=" + name + ", created="
				+ created + ", lastmodified=" + lastmodified + "]";
	}

}
