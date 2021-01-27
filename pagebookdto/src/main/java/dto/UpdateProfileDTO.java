package dto;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class UpdateProfileDTO {

    private String userId;

    private String userName;

    private String userImgURL;

    @Override
    public String toString() {
        return "UpdateProfileDTO{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userImgURL='" + userImgURL + '\'' +
                '}';
    }
}