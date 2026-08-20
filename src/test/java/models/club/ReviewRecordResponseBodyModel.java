package models.club;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public record ReviewRecordResponseBodyModel(
        long id,
        long club,
        UserRecordResponseBodyModel user,
        String review,
        int assessment,
        int readPages,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
        Instant created,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
        Instant modified
) {
}
