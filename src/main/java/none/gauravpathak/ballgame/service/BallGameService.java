package none.gauravpathak.ballgame.service;

import none.gauravpathak.ballgame.BallGameRepository;
import none.gauravpathak.ballgame.model.BallGame;
import none.gauravpathak.ballgame.model.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BallGameService {

    private BallGameRepository ballGameRepository;

    @Autowired
    public BallGameService(BallGameRepository ballGameRepository) {
        this.ballGameRepository = ballGameRepository;
    }

    public String ballGameSave(Input input) {
        BallGame ballGame = new BallGame();
        ballGame.setValue(input.getInputReceived());
        ballGameRepository.save(ballGame);
        return null;
    }

    public List<BallGame> listOfBallGame(){
        return ballGameRepository.findAll();
    }


}
