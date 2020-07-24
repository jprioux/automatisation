# Automation priority: null
# Automation status: TRANSMITTED
# Test case importance: LOW
# language: fr
Fonctionnalité: US998- Gestion des erreurs pour les paramètres liés à un cas de test bdd
  
  Plan du Scénario: Saisie incorrecte d'un paramètre d'une action
  Etant donné que je suis sur la page d'authentification Squash
  Etant donné que je me connecte en tant que "admin" avec mot de passe "admin"
  Etant donné que je suis sur la page d'accueil Squash
  Etant donné que je navigate au CdT BBD dont l'id est 240
  
  Etant donné que je saisis le texte pour une nouvelle action "Today is "
  Etant donné que je saisis le param pour une nouvelle action <saisie>
  Quand j'ajoute un nouveau pas de test avec l'action saisie
  Alors je suis dans le cas suivant : <cas>
  Et le message suivant est affiché dans une pop up :
  """
  Le nom d'au moins un des paramètres est invalide :
  Il doit être saisi entre les caractères < et >.
  Seules les lettres majuscules, minuscules, les chiffres et les tirets - et _ sont autorisés.
  Il ne peut pas être vide.
  """
  
  Exemples:
      
    |cas                                                 |saisie     |
    |chevron ouvrant présent mais pas de chevron fermant |"<param"   |
    |pas de paramètre renseigné entre les chevrons       |"<>"       |
    |caractère interdit                                  |"<&>"      |
