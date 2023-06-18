package com.example.Trab_TPI.controller;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FigurinhaController {

    @PostMapping("/gerar-figurinha")
    public String gerarFigurinha(@RequestParam("url") String url,
                                 @RequestParam("titulo") String titulo,
                                 @RequestParam("nomeArquivo") String nomeArquivo) throws IOException {
        BufferedImage imagemOriginal = ImageIO.read(new URL(url));
        BufferedImage novaImagem = new BufferedImage(imagemOriginal.getWidth(), imagemOriginal.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = novaImagem.createGraphics();
        g2d.drawImage(imagemOriginal, 0, 0, null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.drawString(titulo, 10, 40);
        g2d.dispose();

        File output = new File("saída/" + nomeArquivo + ".png");
        ImageIO.write(novaImagem, "png", output);

        return "redirect:/";
    }
}
