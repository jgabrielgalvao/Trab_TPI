package com.example.Trab_TPI.controller;

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
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // Configurar a fonte para escrever na imagem
        Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 100);
        graphics.setFont(fonte);
        graphics.setColor(Color.YELLOW);

        // Escrever uma mensagem na nova imagem
        graphics.drawString(titulo, 200, novaAltura - 80);

        String caminhoSaida = "src/main/saida/"; // Caminho relativo à pasta do projeto
        File diretorioSaida = new File(caminhoSaida);
        if (!diretorioSaida.exists()) {
            diretorioSaida.mkdirs();
        }

        File output = new File(diretorioSaida, nomeArquivo + ".png");
        ImageIO.write(novaImagem, "png", output);

        return "redirect:/";
    }
}
