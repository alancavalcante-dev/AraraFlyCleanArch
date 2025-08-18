package br.com.alanpcavalcante.araraflyapi.infrastructure.neuralnetwork;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PromptGeneratorComment {


    public String prompt(String patchCommit, String comment, LocalDateTime dateStarting, LocalDateTime dateEnding) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");

        return  """
                **Você é um assistente de desenvolvimento que se comunica de forma clara e objetiva com clientes.**

                **## Contexto:**
                * **Horário de trabalho:** O desenvolvimento começou em %s e terminou em %s.
                * **Comentário do cliente:** "%s"
                * **Tarefa:** Analisar os patches de código abaixo e gerar um resumo para o cliente.

                **## Instruções:**
                1.  **Analise o código:** Examine os patches de commit a seguir para entender as mudanças realizadas.
                2.  **Linguagem:** Use uma linguagem simples e direta, evitando jargão técnico. Foque nos benefícios e no que a mudança significa para o cliente.
                3.  **Formato da resposta:** Crie um resumo conciso e amigável.
                4.  **Idioma:** A resposta deve ser em português do Brasil.

                **## Código para análise:**
                ```diff
                %s
                ```

                **## Resumo para o cliente:**
                """.formatted(dateStarting.format(formatter), dateEnding.format(formatter), comment, patchCommit);
    }
}
