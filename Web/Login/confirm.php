

  <!DOCTYPE html>
  <html>
  <head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <style>
  form {
      border: 3px solid #f1f1f1;
  }

  input[type=number] {
      width: 100%;
      padding: 12px 20px;
      margin: 8px 0;
      display: inline-block;
      border: 1px solid #ccc;
      box-sizing: border-box;
  }

  button {
      background-color: #4CAF50;
      color: white;
      padding: 14px 20px;
      margin: 8px 0;
      border: none;
      cursor: pointer;
      width: 100%;
  }

  button:hover {
      opacity: 0.8;
  }

  .cancelbtn {
      width: auto;
      padding: 10px 18px;
      background-color: #f44336;
  }

  .container {
      padding: 16px;
  }

  span.psw {
      float: right;
      padding-top: 16px;
  }

  /* Change styles for span and cancel button on extra small screens */
  @media screen and (max-width: 300px) {
      span.psw {
         display: block;
         float: none;
      }
      .cancelbtn {
         width: 100%;
      }
  }
  </style>
  </head>
  <body>

  <h2>Login Form</h2>

  <form action="confirmotp.php" method = "post">


    <div class="container">
      <label><b>enter otp</b></label>
      <input type="number" placeholder="OTP" name="otp" required>


      <button type="submit">Register</button>

    </div>


  </form>

  </body>
  </html>
