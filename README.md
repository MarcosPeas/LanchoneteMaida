## Projeto Lanchonete Maida

Este projeto consiste em uma API para pedidos de comida de uma lanchonete. Essa API deve oferecer o controle de pedidos de comidas, bebidas e sobremesas. Além de controlar usuários produtos, relatórios, envio de e-mails para recuperação se senhas, autenticação e controle de acesso.

O projeto se enquadra nos requisitos que seguem:

### Requisitos funcionais
* Cadastro de usuários
* Alteração de usuários
* Alteração de senha
* Listagem de usuários
* Remoção de usuários
* Cadastro de produtos
* Alteração de produtos
* Listagem de produtos
* Remoção de produtos
* Efetivação de pedidos
* Gerência de pedidos
* Listagem de pedidos
* Cadastro de mensagens
* Alteração de mensagens
* Listagem de mensagens
* Remoção de mensagens

### Requisitos de Negócios
* Cadastro do gestor: A configuração inicial da aplicação deve ser feita com o cadastro do gestor. Somente após isso, que poderá dar seguimento às demais funcionalidades. Nesse cadastro deve ter, pelo menos, o nome do estabelecimento, e-mail e senha de autenticação. Não poderá ter mais de um gestor cadastrado.
* Cadastrar clientes: Os clientes devem se cadastrar na aplicação para poder realizar os pedidos. Nesse cadastro, deve ter, pelo menos, o nome, data de nascimento, telefone e e-mail. Este último deve ser o identificador de unicidade do cliente.
* Login: O login deve ser realizado com e-mail e senha, e deve ter também um tempo de sessão máxima de 10 minutos.
* Cadastrar produtos: Somente o gestor poderá realizar o cadastro dos seus produtos. Os produtos devem ser precificados e classificados (bebida, comida ou sobremesa). O gestor poderá também realizar edição no cadastro e remover da sua cesta de opções de venda.
* Solicitar pedido: O cliente poderá solicitar pedidos, podendo incluir mais de um produto com suas respectivas quantidades. Após o envio, o cliente deve ter como retorno a identificação do pedido, a lista de produtos e o valor total.
* Consultar Pedido: O cliente pode consultar o status do seu pedido e também a lista dos pedidos que já foram finalizados.
* Gerenciar Pedido: O gestor deverá fazer o gerenciamento dos pedidos, informando o status atual do pedido, finalizar ou mesmo cancelar caso não tenha mais em estoque.
* Cancelar Pedido: O cliente poderá também cancelar o pedido enquanto ele não estiver sendo preparado
* O gestor poderá escrever mensagens informativas nos pedidos, alterar as mensagens ou remover as mensagens. O cliente poderá ver as mensagens cadastradas pelo gestor. O cliente só poderá ver as mensagens vinculadas a seus pedidos.

### Diagrama Entidade e Relacionamento
Para melhor visualizar a disposição dos dados da nossa API, segue o Diagrama Entidade e Relacionamento
![image](https://drive.google.com/uc?export=view&id=1lhctVFCC9RZGUWsCrdr4N7IgVHImHL6s)

### Setup do projeto

##### Para rodar o projeto, siga as seguintes instruções:

* Clone o projeto com o git ou faça o download
* Importe o projeto com a sua IDE Java pelo menu File->Import->Maven->Existing Maven Projects
* Crie um banco de dados utilizando o MySQL com o nome lanchonete_maida
* Dentro do projeto, configure a conexão com o banco de dados no arquivo application.properties
* Inicie o banco de dados
* Para o envio de e-mails, coloquei seu e-mail e senha do Google nas configurações de e-mail no arquivo application.properties (NÃO ENVIE SEUS DADOS DE E-MAIL PARA REPOSITÓRIOS PÚBLICOS). O Google bloqueia o acesso à conta do Google a aplicativos menos seguros, para este projeto funcionar corretamente, permita o acesso à conta do Google a aplicativos menos seguros aqui https://myaccount.google.com/lesssecureapps 
* Edite os dados iniciais da lanchonete na linha 27 do arquivo V1__lanchonete_maida.sql no caminho resource/db/migration/mysql
* Edite o nome, e-mail e data de nascimento do gestor do sistema na linha 134 do arquivo V1__lanchonete_maida.sql no caminho resource/db/migration/mysql. Não altere a senha, pois já está encriptada. A senha é 123456. 
* Caso o SwaggerUI não funcione, o token utilizado por ele pode ter expirado. Para obter um token válido para o Swagger e que dure bastante tempo, aumente a validade dos tokens no arquivo application.properties, linha 15 e inicie o projeto. Com o Postman, acesse http://localhost:8080/v1/auth, faça login com os dados do gestor cadastrado, copiei o token gerado e coloque como valor da variável swaggerToken na classe com.lanchonete.maida.swagger.SwaggerConfig. Retorne no arquivo application.properties e defina o tempo de expiração do token como originalmente ou a seu gosto.
* Reinicie o projeto e acesse acesse http://localhost:8080/swagger-ui.html para testar a API.

##### Outras informações
A API está documentada com o Swagger. Utilize o SwaggerUi para ver os endpoits e testá-los.
Sinta-se livre para clonar este projeto a qualquer momento.
