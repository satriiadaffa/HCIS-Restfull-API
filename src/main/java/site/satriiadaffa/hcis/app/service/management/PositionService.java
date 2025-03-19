package site.satriiadaffa.hcis.app.service.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.satriiadaffa.hcis.app.model.management.PositionModel;
import site.satriiadaffa.hcis.app.repository.management.PositionRepository;

import java.util.*;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public List<PositionModel> getAllPositions() {
        return positionRepository.findAll();
    }

    public Optional<PositionModel> getPositionById(Long id) {
        return positionRepository.findById(id);
    }

    public PositionModel createPosition(PositionModel position) {
        return positionRepository.save(position);
    }

    public PositionModel updatePosition(Long id, PositionModel positionDetails) {
        return positionRepository.findById(id)
                .map(position -> {
                    position.setPositionName(positionDetails.getPositionName());
                    return positionRepository.save(position);
                })
                .orElseThrow(() -> new RuntimeException("Position not found"));
    }

    public void deletePosition(Long id) {
        positionRepository.deleteById(id);
    }
}
