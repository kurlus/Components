package asim.hackertest.entities;

public class ArtistAlbums 
{
	private Long artistId;
	private Long albumId;

	public ArtistAlbums()
	{	  
	}

	public ArtistAlbums(Long artistId, Long albumId)
	{	
		this.artistId = artistId;
		this.albumId = albumId;
	}

	public Long getArtistId() {
		return artistId;
	}

	public void setArtistId(Long artistId) {
		this.artistId = artistId;
	}

	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}
	
	@Override
	public String toString() {
		return "ArtistAlbums [artistId=" + artistId + ", albumId=" + albumId + "]";
	}

}
