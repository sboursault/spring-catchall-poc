package poc.arkham.frontoffice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import poc.arkham.frontoffice.manager.InmateManager;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private InmateManager inmateManager;

    @GetMapping
    public String get(Model model) {
        model.addAttribute("inmates", inmateManager.fetchInmates().getResults());
        return "home";
    }

}