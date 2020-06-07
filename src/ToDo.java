import java.time.LocalDate;
public class ToDo {

	 private Long id;
	 private String title;
	 private String username;
	 private String description;
	 private LocalDate date;
	 private boolean status;

	 protected ToDo() { }
	 
	 public ToDo(long id, String title, String username, String description, LocalDate date, boolean isDone) {
	        super();
	        this.id = id;
	        this.title = title;
	        this.username = username;
	        this.description = description;
	        this.date = date;
	        this.status = isDone;
	 }

	 public ToDo(String title, String username, String description, LocalDate date, boolean isDone) {
	        super();
	        this.title = title;
	        this.username = username;
	        this.description = description;
	        this.date = date;
	        this.status = isDone;
	 }
	 
	 @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + (int)(id ^ (id >>> 32));
	        return result;
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        ToDo other = (ToDo) obj;
	        if (id != other.id)
	            return false;
	        return true;
	    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	 
	 
}
