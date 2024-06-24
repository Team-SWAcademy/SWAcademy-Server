package carbonneutral.academy.utils.s3.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class S3UploadRequest {

    private int userId;
    private String dirName;
}
