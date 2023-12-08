const newContactElement = (item) => {
    
    const template = document.getElementById("contacts-template")
    
    const contactElement = document.importNode(template.content, true)
    
    const itensContacts = contactElement.querySelectorAll("span")

    itensContacts[0].innerText = item.id
    itensContacts[1].innerText = item.name
    itensContacts[2].innerText = item.phone

    return contactElement
}

const LoadContacts = async () =>{

    const response = await fetch("http://localhost:8080/contact/all")
    const datas = await response.json()
    console.log(datas)

    datas.forEach(item => {
        const containerContacts = document.getElementById("container-contacts")
        
        const contactElement = newContactElement(item)

        containerContacts.append(contactElement)
    })
}

const newContact = async () => {
    const nameCreate = document.getElementById("nameCreate")
    const phoneCreate = document.getElementById("phoneCreate")

    if(nameCreate.value == "" || phoneCreate.value == ""){
        alert("Campo vazio!")
        return
    }

    const contact = {
        name: nameCreate.value,
        phone: phoneCreate.value
    }

    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(contact)
    }

    const response = await fetch("http://localhost:8080/contact", init)
    const datas = await response.json()

    const containerContacts = document.getElementById("container-contacts")

        
    const contactElement = newContactElement(datas)

    containerContacts.append(contactElement)
}

const deleteContact = async () => {
    const idElement = document.getElementById("idDelete")

    if(idElement.value == ""){
        alert("Campo vazio!")
        return
    }

    const init = {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json"
        }
    }

    const response = await fetch("http://localhost:8080/contact/" + idElement.value, init)

    location.reload()
}

const editContact = async () => {
    const idEdit = document.getElementById("idEdit")
    const nameEdit = document.getElementById("nameEdit")
    const phoneEdit = document.getElementById("phoneEdit")

    if(idEdit.value == "" || nameEdit.value == "" || phoneEdit.value == ""){
        alert("Campo vazio!")
        return
    }
    
    const url = "http://localhost:8080/contact/" + idEdit.value

    const init = {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: nameEdit.value,
            phone: phoneEdit.value
        })
    }

    const responsePut = await fetch(url , init)

    location.reload()
}

window.onload = () => {
 
    LoadContacts()

    const btnAddContact = document.getElementById("btnAddContact")
    btnAddContact.onclick = newContact

    const btnDelete = document.getElementById("btnDelete")
    btnDelete.onclick = deleteContact

    const btnEditContact = document.getElementById("btnEditContact")
    btnEditContact.onclick = editContact

    console.log("iniciado")
    }
