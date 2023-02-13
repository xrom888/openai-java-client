package com.lipcha.model.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lipcha.parser.LocalDateTimeDeserializer;
import com.lipcha.parser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.*;

public class Model {

	private final String id;
	private final String object;
	private final LocalDateTime created;
	private final String ownedBy;
	private final List<Permission> permission;
	private final String root;
	private final String parent;

	@JsonCreator
	public Model(@JsonProperty("id") String id,
				 @JsonProperty("object") String object,
				 @JsonProperty("created") @JsonDeserialize(using = LocalDateTimeDeserializer.class) LocalDateTime created,
				 @JsonProperty("owned_by") String ownedBy,
				 @JsonProperty("permission") List<Permission> permission,
				 @JsonProperty("root") String root,
				 @JsonProperty("parent") String parent) {
		this.id = id;
		this.object = object;
		this.created = created;
		this.ownedBy = ownedBy;
		this.permission = permission;
		this.root = root;
		this.parent = parent;
	}

	private Model(Builder builder) {
		this.id = builder.id;
		this.object = builder.object;
		this.created = builder.created;
		this.ownedBy = builder.ownedBy;
		this.permission = builder.permission;
		this.root = builder.root;
		this.parent = builder.parent;
	}

	public static class Builder {

		private String id;
		private String object;
		private LocalDateTime created;
		private String ownedBy;
		private List<Permission> permission;
		private String root;
		private String parent;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder object(String object) {
			this.object = object;
			return this;
		}

		public Builder created(LocalDateTime created) {
			this.created = created;
			return this;
		}

		private Builder ownedBy(String ownedBy) {
			this.ownedBy = ownedBy;
			return this;
		}

		private Builder permission(List<Permission> permission) {
			Objects.requireNonNull(permission, "'permission' should not be null");
			this.permission = Collections.unmodifiableList(permission);
			return this;
		}

		private Builder root(String root) {
			this.root = root;
			return this;
		}

		private Builder parent(String parent) {
			this.parent = parent;
			return this;
		}

		public Model build() {
			if (permission == null) {
				permission = Collections.emptyList();
			}
			return new Model(this);
		}
	}

	public static Builder newBuilder() {
		return new Builder();
	}
	
	@JsonProperty("id")
	public String id() {
		return id;
	}

	@JsonProperty("object")
	public String object() {
		return object;
	}

	@JsonProperty("created")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime created() {
		return created;
	}

	@JsonProperty("owned_by")
	public String ownedBy() {
		return ownedBy;
	}

	@JsonProperty("permission")
	public List<Permission> permission() {
		return permission;
	}

	@JsonProperty("root")
	public String root() {
		return root;
	}

	@JsonProperty("parent")
	public String parent() {
		return parent;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Model model = (Model) o;
		return Objects.equals(id, model.id) && Objects.equals(object, model.object) && Objects.equals(created, model.created) && Objects.equals(ownedBy, model.ownedBy) && Objects.equals(permission, model.permission) && Objects.equals(root, model.root) && Objects.equals(parent, model.parent);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, object, created, ownedBy, permission, root, parent);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Model.class.getSimpleName() + "[", "]")
				.add("id='" + id + "'")
				.add("object='" + object + "'")
				.add("created=" + created)
				.add("ownedBy='" + ownedBy + "'")
				.add("permission=" + permission)
				.add("root='" + root + "'")
				.add("parent='" + parent + "'")
				.toString();
	}
}