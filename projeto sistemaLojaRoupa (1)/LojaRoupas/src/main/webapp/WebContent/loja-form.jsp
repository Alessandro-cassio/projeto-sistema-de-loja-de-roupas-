<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${loja == null ? 'Adicionar Nova Loja' : 'Editar Loja'}</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            text-align: center;
        }

        .container {
            max-width: 400px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
        }

        form {
            text-align: left;
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="number"] {
            width: calc(100% - 22px);
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"],
        input[type="button"] {
            display: inline-block;
            padding: 10px 20px;
            margin: 10px 5px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover,
        input[type="button"]:hover {
            background-color: #0056b3;
        }

        .button-group {
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>${loja == null ? 'Adicionar Nova Loja' : 'Editar Loja'}</h1>
        <form action="${loja == null ? 'inserir' : 'atualizar'}" method="post">
            <input type="hidden" name="id" value="${loja.id}">
            <label for="nome">Nome:</label>
            <input type="text" name="nome" value="${loja == null ? '' : loja.nome}" required><br>
            <label for="quantidadeItens">Quantidade de Itens:</label>
            <input type="number" name="quantidadeItens" value="${loja == null ? '' : loja.quantidadeItens}" required><br>
            <label for="precoMedio">Preço Médio:</label>
            <input type="number" step="0.01" name="precoMedio" value="${loja == null ? '' : loja.precoMedio}" required><br>
            <div class="button-group">
                <input type="submit" value="${loja == null ? 'Adicionar' : 'Atualizar'}">
                <a href="listar"><input type="button" value="Cancelar"></a>
            </div>
        </form>
    </div>
</body>
</html>

