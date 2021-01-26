package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuestCartDTO {
    private String sessionId;
    ArrayList<MappingDTO> list;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public ArrayList<MappingDTO> getList() {
        return list;
    }

    public void setList(ArrayList<MappingDTO> list) {
        this.list = list;
    }
}
