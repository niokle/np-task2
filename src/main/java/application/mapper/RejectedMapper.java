package application.mapper;

import application.domain.Rejected;
import application.dto.RejectedDto;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class RejectedMapper {
    public RejectedDto rejectedToRejectedDto(Rejected rejected) {
        return new RejectedDto(rejected.getId(), rejected.getRecord(), rejected.getDescription(), rejected.getFileName());
    }

    public List<RejectedDto> rejectedsToRejectedsDtos(List<Rejected> rejecteds) {
        List<RejectedDto> rejectedsDtos = new ArrayList<>();
        for (Rejected rejected : rejecteds) {
            rejectedsDtos.add(rejectedToRejectedDto(rejected));
        }
        return rejectedsDtos;
    }
}
