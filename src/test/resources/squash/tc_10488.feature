# Automation priority: null
# Automation status: TRANSMITTED
# Test case importance: LOW
# language: fr
Fonctionnalité: US-742 - Supprimer des paramètres d'une action
  
  Scénario: Suppression de paramètres et plus de correspondance avec une action de la bibliothèque
  Etant donné que je suis sur la page d'authentification Squash
  Etant donné que je me connecte en tant que "admin" avec mot de passe "admin"
  Etant donné que je suis sur la page d'accueil Squash
  
  Etant donné que il existe une action "je saisis quelque chose" d'id 18 dans la librairie 1
  Etant donné que je navigate au CdT BBD dont l'id est 240
  Quand je souhaite modifier le pas de test avec l'action "je saisis quelque chose \"à supprimer\""
  Et que je supprime dans le pas de test le paramètre pour saisir "je saisis quelque chose"
  Et que je valide ma modification du pas de test
  Alors le pas de test est bien modifié avec l'action "je saisis quelque chose"
