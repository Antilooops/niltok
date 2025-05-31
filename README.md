# Niltok

## A propos

Niltok est une application d'ecommerce proposant un grand catalogue de produits. Que ce soit des vêtements pour femmes ou pour hommes, de l'électronique ou des bijoux vous trouverez tout ce que vous voulez sur cette application.

## Prérequis

Le développement de l'application se fait en utilisant l'IDE Android Studio avec les éléments suivants

- Android 13 ou plus
- Gradle 8.9.2

L'application utilise utilise les dépendences suivantes :

- Retrofit2 2.8.1
- Converter Moshi 2.8.1
- Okhttp3 Logging Interceptor 4.4.0
- Glide 4.3.0
- ZXing Android Embedded 4.3.0

Les produits sont obtenues à l'aide de l'API [fakestoreapi](https://fakestoreapi.com/)

## Installation

1. Cloner le projet dans le dossier de votre choix

```
# cd <chemin/vers/dossier_de_téléchargement>
# git clone https://github.com/Antilooops/niltok.git
```

2. Ouvrir le projet dans android studio
3. Syncroniser les dépendences

## Démarrage de l'application

Build et démarrer le projet soit :

- dans l'environnement virtuel d'android
- sur un téléphone équipé d'Android 13 ou plus

## Fonctionnalités

### Navbar

Dans les différentes pages de l'application (sauf la page d'accueil), il est possible d'ouvrir la
navbar en appuyant sur le symbole se situant en haut à gauche. Elle permet d'accèder au différentes
pages de l'application :

- page d'accueil
- magasin (liste des produits)
- scan d'un QR code
- panier

### Recherche de produits

Dans le magasin, il est possible de faire une recherche en appuyant sur la loupe de la barre de
recherche puis en entrant le nom du produits ou des mots clés.

### Fiche produit

Les produits aléatoires de la page d'accueil et les produits du magasin sont cliquables et mènent
vers leur fiche produit assiociée

### Panier

Les produits peuvent être ajoutés dans le panier soit depuis la liste de produits ou depuis une
fiche produit en cliquant sur l'icon "+". Le button peut-être appuyer autant de fois qu'il faut
ajouter de produits.

Dans le panier il également possible de changer la quantité de produits soit en appuyant sur l'icon
"+" pour augmenter la quantité ou sur l'icon "-" pour réduire la quantité. Si le quantité d'un
produit est à 1 et qu'on diminue sa quantité, le produit est alors supprimer du panier.

### Scan de QR code

Sur chaque fiche produit il est possible d'afficher le qr code d'un article en appuyant sur l'icon
du QR code.

Pour scaner un QR code, il suffit soit d'appuyer sur le bouton du menu ou sur l'onglet associé. Cela
ouvre la caméra et il est possible de scaner le QR code d'un produit. Lorsqu'un QR code d'un produit
existant est lu, cela ouvre alors la fiche produit associé.

