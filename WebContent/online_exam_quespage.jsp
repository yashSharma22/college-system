<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="css/global.css">
    <link rel="stylesheet" href="css/online_Exam_Page.css">

    <title>Hello, world!</title>
</head>

<body>
    <script type="text/javascript">
        var currentQues = 1;
    </script>
    <header>
        <nav class="navbar navbar-light">
            <div class="container-fluid">
                <a class="navbar-brand ms-5" href="#">
                    <h1 class="fw-bold text-white">MDEC</h1>
                </a>
                <div>
                    <div class="fw-bold fs-4 text-white mx-5">
                        <span>Time :- &nbsp;</span><span id="timer">--:--</span>
                    </div>
                </div>
            </div>
        </nav>
    </header>
    <main>
        <div class="container-fluid">
            <div class="row text-center shadow-sm mt-2 mx-1 py-3 border">
                <div class="col fw-bold fs-4"><span>Exam Name: </span></div>
                <div class="col fw-bold fs-4"><span>Total Marks: </span></div>
                <div class="col fw-bold fs-4"><span>Positive Marking: </span></div>
                <div class="col fw-bold fs-4"><span>Negative Marking: </span></div>
            </div>
        </div>

        <div class="container-fluid">
            <div class="row mx-1 mt-3">
                <div class="col-8 border p-4 shadow-sm">
                    <div class="ques fs-5">
                        Q<span id="questionNo">1</span>. <span id="question"></span>
                    </div>
                    <div class="option-container">
                        <div class="my-3">
                            1. <label>
                                <input class="form-check-input" type="radio" name="option" value="1" />
                                <span id="text-op1"></span>
                            </label>
                        </div>
                        <div class="my-3">
                            2. <label>
                                <input class="form-check-input" type="radio" name="option" value="2" />
                                <span id="text-op2"></span>
                            </label>
                        </div>
                        <div class="my-3">
                            3. <label>
                                <input class="form-check-input" type="radio" name="option" value="3" />
                                <span id="text-op3"></span>
                            </label>
                        </div>
                        <div class="my-3">
                            4. <label>
                                <input class="form-check-input" type="radio" name="option" value="4" />
                                <span id="text-op4"></span>
                            </label>
                        </div>
                    </div>
                    <div class="row text-center">
                        <div class="col">
                            <a onclick="saveAnswer()" class="btn btn-primary">Save</a>
                        </div>
                        <div class="col">
                            <a onclick="gotoQuestion(currentQues-1)"
                                class="btn btn-primary">Previous</a></div>
                        <div class="col">
                            <a onclick="gotoQuestion(currentQues+1)" class="btn btn-primary">Next</a>
                        </div>
                    </div>
                    <div class="fw-bold " id="choice"></div>
                </div>
                <div class="col-4">
                    <div class="text-center border p-4 shadow-sm">
                        <div class="row text-white fs-5 mb-4">
                            <div class="col" style="background-color: #dc3545;">
                                Visited
                            </div>
                            <div class="col" style="background-color: #198754;">
                                Submitted
                            </div>
                        </div>
                        <form action="" method="post">
                            
                            <input type="hidden" name="user-answers" id="ans1"/>
                            <a id="qBtn1" onclick="gotoQuestiontion(1)" class="btn border shadow-sm btn-light">1</a>
    
                            <br>
                            <button id="submitbutton" type="submit" name="action"
                                class="btn text-white mt-3"
                                style="background-image: linear-gradient(135deg, var(--themeColor1) 0%, var(--themeColor2) 100%);">Submit
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>
    
    <footer class="text-center text-white py-2">
        &copy; 2021 MDEC All Rights Reserved.
    </footer>


    <!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

        <script type="text/javascript">
            function Question (ques,op1,op2,op3,op4,status){
                this.ques = ques;
                this.op1 = op1;
                this.op2 = op2;
                this.op3 = op3;
                this.op4 = op4;
                this.status = status;
            }
            var questionList = [];
            // questionList.push(new Question("<%= %>","<%= %>","<%= %>","<%= %>","<%= %>",<%= %>));
            questionList.push(new Question("q1","1","2","3","4",0));
            questionList.push(new Question("q2","1","2","3","4",0));
            questionList.push(new Question("q3","1","2","3","4",0));
            questionList.push(new Question("q4","1","2","3","4",0));
            
            function gotoQuestion(q)
            {
                currentQues=q;
                var e=document.getElementById("qBtn"+q);
                if(questionList[q-1].status == 0)
                {
                    questionList[q-1].status=1;
                    e.classList.replace("btn-light","btn-danger");
                }
                document.getElementById("questionNo").innerHTML=q;
                document.getElementById("question").innerHTML=questionList[q-1].ques;
                document.getElementById("text-op1").innerHTML=questionList[q-1].op1;
                document.getElementById("text-op2").innerHTML=questionList[q-1].op2;
                document.getElementById("text-op3").innerHTML=questionList[q-1].op3;
                document.getElementById("text-op4").innerHTML=questionList[q-1].op4;
                var choice=document.getElementById("choice");
                if(questionList[q-1].status == 2)
                {
                     choice.innerHTML="Your Choice :- "+document.getElementById("ans"+q).value;
                }
                else
                {
                    choice.innerHTML="";
                }
                var op=document.getElementsByName("option");
                op[0].checked=false;
                op[1].checked=false;
                op[2].checked=false;
                op[3].checked=false;
            }
    
    function saveAnswer()
    {
         var op=document.getElementsByName("option");
         if(op[0].checked)
             {
             document.getElementById("ans"+k).value=questionList[currentQues-1].op1;
             changeOptionStatus();
             }
         else if(op[1].checked)
             {
             document.getElementById("ans"+k).value=questionList[currentQues-1].op2;
             changeOptionStatus();
             }
         else if(op[2].checked)
             {
             document.getElementById("ans"+k).value=questionList[currentQues-1].op3;
             changeOptionStatus();
             }
         else if(op[3].checked)
             {
             document.getElementById("ans"+k).value=questionList[currentQues-1].op4;
             changeOptionStatus();
             }
    }
    function changeOptionStatus()
    {
        if(questionList[currentQues-1].status==1)
          {
              questionList[currentQues-1].status=2;
              document.getElementById("qBtn"+k).classList.replace("btn-danger","btn-success");
          }
        var choice=document.getElementById("choice");
           choice.innerHTML="Your Choice :- "+document.getElementById("ans"+k).value;
    }

    gotoQuestion(1);
</script>
<script type="text/javascript">
    var time = 100;
    
    function convertsec(s)
    {
        var min =Math.floor(s/60);
        var sec= s%60;
        if(sec<10)
            sec='0'+sec;
        if(min<10)
            min='0'+min;
        return min + ':' +sec;
    }
    
    var interval=setInterval(countdown,1000);
    function countdown()
    {
        var p=document.getElementById("timer");
        p.innerHTML=convertsec(time);
        time=time-1;   
        
        if(time==-1)
        {
            clearInterval(interval);
            document.getElementById("submitbutton").click();
        }
    }
</script>
</body>

</html>