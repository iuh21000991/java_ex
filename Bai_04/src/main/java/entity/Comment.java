package entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Comment {
	
	@Id
	@Column(name = "comment_date")
	private LocalDate commentDate;
	@Column
	private String contents;
	@Column
	private Integer likes;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "user_name")
	private User user;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;
}
