from flask import Flask, render_template, request, flash, session, redirect, url_for
import requests
from datetime import datetime

app = Flask(__name__)

app.secret_key = "ededhehdmkaeldjamkdkladkan86786456454"

API_URL = "http://127.0.0.1:8888"


@app.route("/")
@app.route("/clients")
@app.route("/index_client", methods=["POST", "GET"])
def index_client():
    response = requests.get(f"{API_URL}/SERVICE-CLIENT/clients")
    datas = []
    if response.status_code == 200:
        datas = response.json()["_embedded"]["clients"]
        for data in datas:
            data["id"] = int(data["_links"]["self"]["href"].split("/")[-1])
    else:
        flash("Erreur lors du chargement de la liste des clients.", "danger")
    return render_template("client/index.html", datas=datas, p="client")


@app.route("/add_client", methods=["POST", "GET"])
def add_client():
    if request.method == "POST":
        response = requests.post(
            f"{API_URL}/SERVICE-CLIENT/clients",
            json={
                "nom": request.form["nom"],
                "prenom": request.form["prenom"],
                "email": request.form["email"],
            },
        )
        print(response.status_code)
        if response.status_code == 201:
            flash("Client créé avec succès", "success")
            return redirect(url_for("index_client"))
        else:
            flash("Une erreur est survenue", "danger")

    return render_template("client/create.html")


@app.route("/edit_client/<int:id>", methods=["GET", "POST"])
def edit_client(id):
    if request.method == "POST":
        response = requests.put(
            f"{API_URL}/SERVICE-CLIENT/clients/{id}",
            json={
                "nom": request.form["nom"],
                "prenom": request.form["prenom"],
                "email": request.form["email"],
            },
        )
        if response.status_code == 200:
            flash("Client modifié avec succès", "success")
            return redirect(url_for("index_client"))
        else:
            flash("Une erreur est survenue", "danger")
    response = requests.get(f"{API_URL}/SERVICE-CLIENT/clients/{id}")
    if response.status_code == 200:
        data = response.json()
    else:
        flash("Une erreur est survenue", "danger")
        return redirect(url_for("index_client"))
    return render_template("/client/edit.html", data=data)


@app.route("/client_delete/<int:id>", methods=["GET", "POST"])
def client_delete(id):
    response = requests.delete(f"{API_URL}/SERVICE-CLIENT/clients/{id}")
    if response.status_code == 200:
        flash("Client supprimé avec succès", "success")
    else:
        flash("Une erreur est survenue", "danger")
    return redirect(url_for("index_client"))


@app.route("/produits")
@app.route("/index_produit", methods=["POST", "GET"])
def index_produit():
    response = requests.get(f"{API_URL}/SERVICE-PRODUIT/produits")
    datas = []
    if response.status_code == 200:
        datas = response.json()["_embedded"]["produits"]
        for data in datas:
            data["id"] = int(data["_links"]["self"]["href"].split("/")[-1])
    else:
        flash("Erreur lors du chargement de la liste des produits.", "danger")
    return render_template("produit/index.html", datas=datas)


@app.route("/add_produit", methods=["POST", "GET"])
def add_produit():
    if request.method == "POST":
        response = requests.post(
            f"{API_URL}/SERVICE-PRODUIT/produits",
            json={
                "nom": request.form["nom"],
                "prix": request.form["prix"],
                "description": request.form["description"],
            },
        )
        if response.status_code == 201:
            flash("Produit créé avec succès", "success")
            return redirect(url_for("index_produit"))
        else:
            flash("Une erreur est survenue", "danger")

    return render_template("produit/create.html")


@app.route("/edit_produit/<int:id>", methods=["GET", "POST"])
def edit_produit(id):
    if request.method == "POST":
        response = requests.put(
            f"{API_URL}/SERVICE-PRODUIT/produits/{id}",
            json={
                "nom": request.form["nom"],
                "prix": float(request.form["prix"]),
                "description": request.form["description"],
            },
        )
        if response.status_code == 200:
            flash("Produit modifié avec succès", "success")
            return redirect(url_for("index_produit"))
        else:
            flash("Une erreur est survenue", "danger")
    response = requests.get(f"{API_URL}/SERVICE-PRODUIT/produits/{id}")
    if response.status_code == 200:
        data = response.json()
    else:
        flash("Une erreur est survenue", "danger")
        return redirect(url_for("index_produit"))
    return render_template("/produit/edit.html", data=data)


@app.route("/produit_delete/<int:id>", methods=["GET", "POST"])
def produit_delete(id):
    response = requests.delete(f"{API_URL}/SERVICE-PRODUIT/produits/{id}")
    if response.status_code == 200:
        flash("Produit supprimé avec succès", "success")
    else:
        flash("Une erreur est survenue", "danger")
    return redirect(url_for("index_produit"))


@app.route("/factures")
@app.route("/index_facture", methods=["POST", "GET"])
def index_facture():
    response = requests.get(f"{API_URL}/SERVICE-FACTURATION/factures")
    datas = []
    if response.status_code == 200:
        datas = response.json()["_embedded"]["factures"]
        for data in datas:
            data["id"] = int(data["_links"]["self"]["href"].split("/")[-1])
            data["date"] = str(data["date"]).split("T")[0]
            response = requests.get(
                f"{API_URL}/SERVICE-FACTURATION/factures/pf/{data['id']}"
            )
            if response.status_code == 200:
                infos = response.json()
                data["client"] = infos["client"]
                data["details"] = infos["details"]
    else:
        flash("Erreur lors du chargement de la liste des factures.", "danger")
    return render_template("facture/index.html", datas=datas)


@app.route("/add_facture", methods=["POST", "GET"])
def add_facture():
    if request.method == "POST":
        response = requests.post(
            f"{API_URL}/SERVICE-FACTURATION/factures",
            json={
                "client_id": request.form["client_id"],
                "date": datetime.now().date().__str__().split(".")[0],
            },
        )
        if response.status_code == 201:
            flash("Facture créée avec succès", "success")
            return redirect(url_for("index_facture"))
        else:
            flash("Une erreur est survenue", "danger")
    clients = []
    response = requests.get(f"{API_URL}/SERVICE-CLIENT/clients")
    if response.status_code == 200:
        clients = response.json()["_embedded"]["clients"]
        for client in clients:
            client["id"] = int(client["_links"]["self"]["href"].split("/")[-1])
    else:
        flash("Une erreur est survenue", "danger")
        return redirect(url_for("index_facture"))
    return render_template("facture/create.html", clients=clients)


@app.route("/edit_facture/<int:id>", methods=["GET", "POST"])
def edit_facture(id):
    if request.method == "POST":
        response = requests.put(
            f"{API_URL}/SERVICE-FACTURATION/factures/{id}",
            json={
                "client_id": request.form["client_id"],
            },
        )
        if response.status_code == 200:
            flash("Facture modifiée avec succès", "success")
            return redirect(url_for("index_facture"))
        else:
            flash("Une erreur est survenue", "danger")
    response = requests.get(f"{API_URL}/SERVICE-FACTURATION/factures/{id}")
    clients = []
    if response.status_code == 200:
        data = response.json()
        response = requests.get(f"{API_URL}/SERVICE-CLIENT/clients")
        if response.status_code == 200:
            clients = response.json()["_embedded"]["clients"]
            for client in clients:
                client["id"] = int(client["_links"]["self"]["href"].split("/")[-1])
        else:
            flash("Une erreur est survenue", "danger")
            return redirect(url_for("index_facture"))
    else:
        flash("Une erreur est survenue", "danger")
        return redirect(url_for("index_facture"))
    return render_template("/facture/edit.html", data=data, clients=clients)


@app.route("/facture_delete/<int:id>", methods=["GET", "POST"])
def facture_delete(id):
    response = requests.delete(f"{API_URL}/SERVICE-FACTURATION/factures/{id}")
    if response.status_code == 200:
        flash("Facture supprimée avec succès", "success")
    else:
        flash("Une erreur est survenue", "danger")
    return redirect(url_for("index_facture"))


@app.route("/fct/<int:fctid>/index_detail", methods=["POST", "GET"])
def index_detail(fctid):
    datas = {}
    response = requests.get(f"{API_URL}/SERVICE-FACTURATION/factures/pf/{fctid}")
    if response.status_code == 200:
        infos = response.json()
        datas["facture"] = infos
        datas["client"] = infos["client"]
        datas["details"] = infos["details"]
    else:
        flash("Erreur lors du chargement de la liste des details.", "danger")
    return render_template("detail/index.html", fctid=fctid, datas=datas)


@app.route("/fct/<int:fctid>/add_detail", methods=["POST", "GET"])
def add_detail(fctid):
    if request.method == "POST":
        response = requests.post(
            f"{API_URL}/SERVICE-FACTURATION/detailFactures",
            json={
                "facture_id": fctid,
                "produit_id": request.form["produit_id"],
                "quantite": request.form["quantite"],
            },
        )
        print(response.json())
        if response.status_code == 201:
            flash("Détail ajouté à la facture avec succès", "success")
        else:
            flash("Une erreur est survenue", "danger")
    produits = []
    response = requests.get(f"{API_URL}/SERVICE-PRODUIT/produits")
    if response.status_code == 200:
        produits = response.json()["_embedded"]["produits"]
        for produit in produits:
            produit["id"] = int(produit["_links"]["self"]["href"].split("/")[-1])
    else:
        flash("Une erreur est survenue lors du chargement des produis", "danger")
        return redirect(url_for("index_detail", fctid=fctid))
    return render_template("detail/create.html", fctid=fctid, produits=produits)


@app.route("/fct/<int:fctid>/edit_detail/<int:id>", methods=["GET", "POST"])
def edit_detail(fctid, id):
    if request.method == "POST":
        response = requests.put(
            f"{API_URL}/SERVICE-FACTURATION/detailFactures/{id}",
            json={
                "facture_id": fctid,
                "produit_id": request.form["produit_id"],
                "quantite": request.form["quantite"],
            },
        )
        if response.status_code == 200:
            flash("Détail de la facture modifié avec succès", "success")
            return redirect(url_for("index_detail", fctid=fctid))
        else:
            flash("Une erreur est survenue", "danger")
    response = requests.get(f"{API_URL}/SERVICE-FACTURATION/detailFactures/{id}")
    produits = []
    if response.status_code == 200:
        data = response.json()
        response = requests.get(f"{API_URL}/SERVICE-PRODUIT/produits")
        if response.status_code == 200:
            produits = response.json()['_embedded']['produits']
            for produit in produits:
                produit['id'] = int(produit['_links']['self']['href'].split("/")[-1])
        else:
            flash("Une erreur est survenue", "danger")
            return redirect(url_for("index_detail", fctid=fctid))
    else:
        flash("Une erreur est survenue", "danger")
        return redirect(url_for("index_detail", fctid=fctid))
    return render_template("/detail/edit.html", fctid=fctid, data=data, produits=produits)


@app.route("/fct/<int:fctid>/detail_delete/<int:id>", methods=["GET", "POST"])
def detail_delete(fctid, id):
    response = requests.delete(f"{API_URL}/SERVICE-FACTURATION/detailFactures/{id}")
    if response.status_code == 200:
        flash("Détail de la facture supprimé avec succès", "success")
    else:
        flash("Une erreur est survenue", "danger")
    return redirect(url_for("index_detail", fctid=fctid))


if __name__ == ("__main__"):
    app.run(debug=True)
