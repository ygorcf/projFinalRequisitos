# Projeto Final de Requisitos de Software
Repositório para o projeto final de requisitos de software, criação do app  IluminaTI.

## Como baixar o projeto

Baixar o Git:
1. Abra o link: [Download Git](https://git-scm.com/downloads).
2. Selecione seu sistema operacional.
3. Execute o arquivo que foi baixado.
4. A instalação é só apertar em "next" até aparecer o botão de "instalar", então clique em "instalar".

Na página inicial do repositório ([Página Inicial](https://github.com/ygorcf/projFinalRequisitos)):
1. Clique em \"Clone or download\".
2. Então copie a URL que irá aparecer.
3. Abra o CMD(Prompt de comando).
4. Abra a pasta onde ficará o projeto, usando o comando "cd <nome da pasta>".
5. Execute o comando "git clone \<link\>", com a url que foi copiada no lugar de '\<link\>'.

## Aplicativo

### Como abrir o projeto:

Baixar o Android Studio:
1. Abra o link: [Download Android Studio](https://developer.android.com/studio/index.html?hl=pt-br).
2. Clique em "Download Android Studio".
3. Aceite os termos, e então irá redirecionar para uma página explicando a instalação do android studio.

Abrir o projeto:
1. Abra o Android Studio.
2. Caso não tiver nenhum projeto aberto pule para o próximo passo, se tiver algum projeto aberto, clique em "File" > "Close Project".
3. Clique em "Import project(Gradle, Eclipse ADT, etc.)".
4. Abra a pasta onde foi feito o download do projeto e escolha a pasta "IluminaTI", que está em "src" > "android", depois aperte em "OK".
5. Então o Android studio irá abrir e configurar o projeto, caso falte algo irá aparecer um erro na parte de baixo e um link para consertar, e então é só clicar no link.

### Como executar:

#### Pelo Android Studio:

Com o projeto aberto no Android Studio:
1. Clique em "Run" > "Run 'app'".
2. Escolha o dispositivo que irá executar o aplicativo e clique em "OK". Em caso de dúvidas siga as instruções em [Instruções para executar aplicativo](https://developer.android.com/studio/run/device.html).

#### Pela APK:

Com o repositório aberto:
1. Clique em "Clone or download" > "Download ZIP".
2. Descompacte o arquivo.
3. Abra a pasta "src" > "android" > "apk".
4. Copie o arquivo "iluminaTI.apk" e cole em alguma pasta no celular, escolha uma que você consiga entrar depois como o "Documents".
5. Abra a pasta pelo celular e clique no arquivo da APK "iluminaTI.apk".
6. Se pedir permição para instalar o aplicativo, clique em "Configurações" e marque a opção "Fontes desconhecidas".

## Servidor

### Como executar:

1. Abra o CMD(Prompt de comando).
2. Abra a pasta onde foi feito o download do projeto e escolha a pasta "miniucb", que está em "src" > "server".
3. Execute o comando "npm run start".
