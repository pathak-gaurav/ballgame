package none.gauravpathak.ballgame.controller;

import none.gauravpathak.ballgame.model.BallGame;
import none.gauravpathak.ballgame.model.Input;
import none.gauravpathak.ballgame.service.BallGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class BallGameController {

    private BallGameService ballGameService;

    @Autowired
    public BallGameController(BallGameService ballGameService) {
        this.ballGameService = ballGameService;
    }

    @GetMapping("/")
    public String ballGameHomePage(Model model) {
        Model ballGame = model.addAttribute("input", new Input());
        return "ball_game";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute("ballGame") Input input, Model model) {
        String s = ballGameService.ballGameSave(input);
        model.addAttribute("nodeNameIssue", "Node Name and Parent Node cannot be same. Try different");
        return "redirect:/";
    }

    @GetMapping("/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=ball_game_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<BallGame> ballGameList = ballGameService.listOfBallGame();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"ID", "Input",};
        String[] nameMapping = {"id", "value" };

        csvWriter.writeHeader(csvHeader);

        for (BallGame ballGame : ballGameList) {
            csvWriter.write(ballGame, nameMapping);
        }

        csvWriter.close();

    }
}
