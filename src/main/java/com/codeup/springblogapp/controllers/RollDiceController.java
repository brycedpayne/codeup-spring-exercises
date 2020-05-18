package com.codeup.springblogapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String showGuessForm() {
        return "rollDice";
    }

    @GetMapping("/roll-dice/{guess}")
    public String showGuessForm(@PathVariable Integer guess, Model model) {
        int dieValue = (int) (Math.ceil(Math.random()*6));
        String msg = "";
        model.addAttribute("yourGuess", "");
        model.addAttribute("dieRoll", "");
        if (guess != null){
            msg = guess == dieValue ? "You guessed the correct value" : "You did not guess the right value, sorry!";
            model.addAttribute("yourGuess", "You guessed: " + guess);
            model.addAttribute("dieRoll", "The die rolled: " + dieValue);
        }
        model.addAttribute("msg",msg);
        return "rollDice";
    }
}
