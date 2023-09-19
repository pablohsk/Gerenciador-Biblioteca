.Biblioteca API - README
.
.Este é o README para o projeto da API de gerenciamento de biblioteca, desenvolvido em Java com o uso do Spring Boot e Maven. A API permite a gestão de livros e usuários, controle de empréstimos e devoluções, limitação de empréstimos por usuário, além de pesquisar livros por título ou autor e usuários por nome.
.
.Tecnologias Utilizadas
.⦁	Java
.⦁	Spring Boot
.⦁	Spring Data JPA
.⦁	MariaDB
.⦁	Maven
.Estrutura do Projeto
.src
.└── main
.    └── java
.        └── VHL
.            └── bibliotecaapi
.                ├── controlador
.                ├── erros
.                ├── modelos
.                ├── repositorio
.                ├── servico
.                └── BibliotecaApiApplication.java
.
.Funcionalidades
.
.A API oferece as seguintes funcionalidades:
.
.⦁	Adicionar, editar e excluir livros.
.⦁	Adicionar, editar e excluir usuários.
.⦁	Controle de empréstimo e devolução de livros.
.⦁	Limite de 2 empréstimos por usuário.
.⦁	Pesquisar livro por título ou autor.
.⦁	Pesquisar usuário por nome
.
.Configuração
.
.Para executar a aplicação, é necessário configurar a conexão com o banco de dados MariaDB. As configurações podem ser encontradas no arquivo application.properties. Certifique-se de ajustar as propriedades spring.datasource.url, spring.datasource.username e spring.datasource.password para as suas configurações de banco de dados.
.
.Endpoints
.
.A seguir estão listados os principais endpoints da API:
.
.Livros
.GET /livros/porTitulo/{titulo}: Pesquisa livros por título.
.GET /livros/porAutor/{autor}: Pesquisa livros por autor.
.POST /livros: Adiciona um novo livro.
.PUT /livros/{id}: Atualiza informações de um livro.
.DELETE /livros/{id}: Exclui um livro
.
.Usuários
.GET /usuarios/porNome/{nome}: Pesquisa usuários por nome.
.GET /usuarios/porEmail/{email}: Pesquisa usuários por email.
.POST /usuarios: Adiciona um novo usuário.
.PUT /usuarios/{id}: Atualiza informações de um usuário.
.DELETE /usuarios/{id}: Exclui um usuário.
.
.Empréstimos
.POST /emprestimos: Realiza um novo empréstimo.
.PUT /emprestimos/{id}: Atualiza informações de um empréstimo.
.DELETE /emprestimos/{id}: Devolve um livro e finaliza um empréstimo..
.
.Histórico de Commits
.
.O histórico de commits deste projeto pode ser encontrado em um repositório público no GitHub. 
.
.Instruções de Uso
.
.1.	Clone este repositório para a sua máquina local.
.2.	Configure as propriedades do banco de dados no arquivo application.properties.
.3.	Execute a aplicação usando o Maven ou a sua IDE favorita.
.
.Organização e Manutenibilidade
.
.O projeto está organizado em pacotes que seguem uma estrutura lógica para separar responsabilidades. Os nomes de classes e métodos foram escolhidos de forma semântica, visando a legibilidade e manutenibilidade do código.
.
.Desenvolvedor
.
.Este projeto foi desenvolvido por Pablo Fidelis Dias e pode ser encontrado no GitHub: https://github.com/pablohsk/Gerenciador-Biblioteca
.
.Considerações Finais
.
.O projeto da API de gerenciamento de biblioteca visa demonstrar o uso de tecnologias como Spring Boot e Spring Data JPA para criar uma solução de backend para uma biblioteca. As funcionalidades de adicionar, editar e excluir livros e usuários, bem como a gestão de empréstimos, são exemplos de operações que podem ser realizadas por meio desta API.
.
.Para qualquer dúvida ou sugestão, sinta-se à vontade para entrar em contato. Obrigado por utilizar esta API!
