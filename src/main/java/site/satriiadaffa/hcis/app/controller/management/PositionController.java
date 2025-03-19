package site.satriiadaffa.hcis.app.controller.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.satriiadaffa.hcis.app.model.management.PositionModel;
import site.satriiadaffa.hcis.app.service.management.PositionService;

import java.util.*;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping
    public List<PositionModel> getAllPositions() {
        return positionService.getAllPositions();
    }

    @GetMapping("/{id}")
    public Optional<PositionModel> getPositionById(@PathVariable Long id) {
        return positionService.getPositionById(id);
    }

    @PostMapping
    public PositionModel createPosition(@RequestBody PositionModel position) {
        return positionService.createPosition(position);
    }

    @PutMapping("/{id}")
    public PositionModel updatePosition(@PathVariable Long id, @RequestBody PositionModel position) {
        return positionService.updatePosition(id, position);
    }

    @DeleteMapping("/{id}")
    public void deletePosition(@PathVariable Long id) {
        positionService.deletePosition(id);
    }
}