package models.club;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.util.List;

public record UpdateClubResponseBodyModel(
        int id,
        String bookTitle,
        String bookAuthors,
        int publicationYear,
        String description,
        String telegramChatLink,
        long owner,
        List<Integer> members,
        List<ReviewRecordResponseBodyModel> reviews,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
        Instant created,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
        Instant modified
) {
}
