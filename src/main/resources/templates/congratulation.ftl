<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Congratulations</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f0f8ff;
            font-family: Arial, sans-serif;
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100%;
        }

        .congratulations-card {
            background-color: white;
            border: 2px solid #008000;
            border-radius: 10px;
            padding: 20px;
            text-align: center;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .congratulations-card h1 {
            color: #008000;
            font-size: 2.5em;
            margin: 0;
        }

        .congratulations-card p {
            font-size: 1.2em;
            margin: 10px 0;
        }

        .congratulations-card .confetti {
            font-size: 2em;
            margin: 20px 0;
        }

        .congratulations-card button {
            background-color: #008000;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            font-size: 1em;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .congratulations-card button:hover {
            background-color: #005f00;
        }

    </style>
</head>
<body>
<div class="container">
    <div class="congratulations-card">
        <h1>Congratulations!</h1>
        <p>You have successfully won the bid of ${itemName}.</p>
        <div class="confetti">
            🎉 🎉 🎉 🎉 🎉
        </div>
    </div>
</div>
</body>
</html>
