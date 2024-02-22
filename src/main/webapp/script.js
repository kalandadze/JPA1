async function findNumbers() {
    clearList();
    var url = "http://localhost:8989/phoneBook/manageNumber";
    var response = await fetch(url, { method: "GET" });
    if(response.status===402){
        alert("couldn't access the phone numbers");
    }
    var contact1= await response.text();
    const contact2=contact1.split(",");
    for(var i=0;i<contact2.length;i++){
        addMessage(contact2[i]);
    }
}

function addMessage(contact){
    var ul = document.getElementById("numbers");
    var li = document.createElement("li");
    li.appendChild(document.createTextNode(contact));
    ul.appendChild(li);
}

function clearList(){
    var ul = document.getElementById("numbers");
    while(ul.firstChild){
        ul.removeChild(ul.firstChild);
    }
}

async function addContact(){
    var name=document.getElementById("name").value;
    var number=document.getElementById("number").value;
    if(name==""||number==""){
        clearList();
        addMessage("body2134234");
        alert("Name and NUmber is Required");
        return;
    }
    var url = "http://localhost:8989/phoneBook/manageNumber?number="+number+"&name="+name;
    document.getElementById("name").value="";
    document.getElementById("number").value=""
    var response = await fetch(url, { method: "POST" });
    if(response.ok){
        alert("Contact successfully saved")
    }else if(response.status===403){
        alert("Problem encouterd when saving the number");
    }
    findNumbers();
}