package dev.chalmers.photoshare.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import org.springframework.lang.Nullable;

import java.util.Set;

@AutoValue
@JsonDeserialize(builder = Photo.Builder.class)
public abstract class Photo {
    public abstract String getId();
    public abstract @Nullable String getName();
    public abstract @Nullable String getDescription();
    public abstract @Nullable String getPath();
    public abstract @Nullable Set<String> getTags();
    public abstract @Nullable Long getSizeInBytes();

    @AutoValue.Builder
    public abstract static class Builder {

        @JsonCreator
        public static Builder builder() {
            return new AutoValue_Photo.Builder();
        }
        @JsonProperty("id")
        public abstract Builder id(String id);
        public abstract Builder name(String name);
        public abstract Builder description(String description);
        public abstract Builder path(String path);
        public abstract Builder tags(Set<String> tags);
        public abstract Builder sizeInBytes(long sizeInBytes);
        public abstract Photo build();
    }
}

