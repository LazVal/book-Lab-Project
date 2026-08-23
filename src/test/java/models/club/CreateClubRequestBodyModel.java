package models.club;

public record CreateClubRequestBodyModel(String bookTitle, String bookAuthors, int publicationYear, String description,
                                         String telegramChatLink) {
}
