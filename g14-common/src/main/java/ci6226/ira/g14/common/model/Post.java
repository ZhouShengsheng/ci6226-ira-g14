package ci6226.ira.g14.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Post model.
 * 
 * @author Zhou Shengsheng
 *
 */
@XmlRootElement(name = "row")
@XmlAccessorType(XmlAccessType.NONE)
public class Post {

	private String Id;
	private String PostTypeId;
	private String AcceptedAnswerId;
	private String ParentID;
	private String CreationDate;
	private String DeletionDate;
	private String Score;
	private String ViewCount;
	private String Body;
	private String OwnerUserId;
	private String OwnerDisplayName;
	private String LastEditorUserId;
	private String LastEditorDisplayName;
	private String LastEditDate;
	private String LastActivityDate;
	private String Title;
	private String Tags;
	private String AnswerCount;
	private String CommentCount;
	private String FavoriteCount;
	private String ClosedDate;
	private String CommunityOwnedDate;

	@XmlAttribute(name = "Id")
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	@XmlAttribute(name = "PostTypeId")
	public String getPostTypeId() {
		return PostTypeId;
	}

	public void setPostTypeId(String postTypeId) {
		PostTypeId = postTypeId;
	}

	@XmlAttribute(name = "AcceptedAnswerId")
	public String getAcceptedAnswerId() {
		return AcceptedAnswerId;
	}

	public void setAcceptedAnswerId(String acceptedAnswerId) {
		AcceptedAnswerId = acceptedAnswerId;
	}

	@XmlAttribute(name = "ParentID")
	public String getParentID() {
		return ParentID;
	}

	public void setParentID(String parentID) {
		ParentID = parentID;
	}

	@XmlAttribute(name = "CreationDate")
	public String getCreationDate() {
		return CreationDate;
	}

	public void setCreationDate(String creationDate) {
		CreationDate = creationDate;
	}

	@XmlAttribute(name = "DeletionDate")
	public String getDeletionDate() {
		return DeletionDate;
	}

	public void setDeletionDate(String deletionDate) {
		DeletionDate = deletionDate;
	}

	@XmlAttribute(name = "Score")
	public String getScore() {
		return Score;
	}

	public void setScore(String score) {
		Score = score;
	}

	@XmlAttribute(name = "ViewCount")
	public String getViewCount() {
		return ViewCount;
	}

	public void setViewCount(String viewCount) {
		ViewCount = viewCount;
	}

	@XmlAttribute(name = "Body")
	public String getBody() {
		return Body;
	}

	public void setBody(String body) {
		Body = body;
	}

	@XmlAttribute(name = "OwnerUserId")
	public String getOwnerUserId() {
		return OwnerUserId;
	}

	public void setOwnerUserId(String ownerUserId) {
		OwnerUserId = ownerUserId;
	}

	@XmlAttribute(name = "OwnerDisplayName")
	public String getOwnerDisplayName() {
		return OwnerDisplayName;
	}

	public void setOwnerDisplayName(String ownerDisplayName) {
		OwnerDisplayName = ownerDisplayName;
	}

	@XmlAttribute(name = "LastEditorUserId")
	public String getLastEditorUserId() {
		return LastEditorUserId;
	}

	public void setLastEditorUserId(String lastEditorUserId) {
		LastEditorUserId = lastEditorUserId;
	}

	@XmlAttribute(name = "LastEditorDisplayName")
	public String getLastEditorDisplayName() {
		return LastEditorDisplayName;
	}

	public void setLastEditorDisplayName(String lastEditorDisplayName) {
		LastEditorDisplayName = lastEditorDisplayName;
	}

	@XmlAttribute(name = "LastEditDate")
	public String getLastEditDate() {
		return LastEditDate;
	}

	public void setLastEditDate(String lastEditDate) {
		LastEditDate = lastEditDate;
	}

	@XmlAttribute(name = "LastActivityDate")
	public String getLastActivityDate() {
		return LastActivityDate;
	}

	public void setLastActivityDate(String lastActivityDate) {
		LastActivityDate = lastActivityDate;
	}

	@XmlAttribute(name = "Title")
	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	@XmlAttribute(name = "Tags")
	public String getTags() {
		return Tags;
	}

	public void setTags(String tags) {
		Tags = tags;
	}

	@XmlAttribute(name = "AnswerCount")
	public String getAnswerCount() {
		return AnswerCount;
	}

	public void setAnswerCount(String answerCount) {
		AnswerCount = answerCount;
	}

	@XmlAttribute(name = "CommentCount")
	public String getCommentCount() {
		return CommentCount;
	}

	public void setCommentCount(String commentCount) {
		CommentCount = commentCount;
	}

	@XmlAttribute(name = "FavoriteCount")
	public String getFavoriteCount() {
		return FavoriteCount;
	}

	public void setFavoriteCount(String favoriteCount) {
		FavoriteCount = favoriteCount;
	}

	@XmlAttribute(name = "ClosedDate")
	public String getClosedDate() {
		return ClosedDate;
	}

	public void setClosedDate(String closedDate) {
		ClosedDate = closedDate;
	}

	@XmlAttribute(name = "CommunityOwnedDate")
	public String getCommunityOwnedDate() {
		return CommunityOwnedDate;
	}

	public void setCommunityOwnedDate(String communityOwnedDate) {
		CommunityOwnedDate = communityOwnedDate;
	}

}
