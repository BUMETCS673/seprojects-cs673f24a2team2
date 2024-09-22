# Second-Hand Market

## Project Description

Second-hand trading is gaining popularity among university students. This platform, tailored for BU students, enables buying and selling of used items like phones, computers, and furniture, etc. It aims to offer a convenient way to trade goods within the university, promoting sustainability and benefiting the community. Our goal is to foster an eco-friendly, green, and inclusive environment. Technically, we use the latest tech stack. Since this is a B/S system, we use Java with Spring Boot and MySQL for the backend, and Node.js with Vue for the frontend. For CI/CD, we rely on GitHub Actions, and the system is deployed on the cloud using Docker and Kubernetes.

![image](demo/image.png)

## Related Work

A few well-known examples include Facebook Marketplace, Craigslist, and OfferUp. These platforms allow users to buy and sell items within local communities. While they serve a broad audience, they do not focus on specific groups like university students. 

**Facebook Marketplace:** (1) Target Audience: Open to the general public, allowing people to buy and sell within their local areas.(2) Key Features: Offers item listings with images, location-based browsing, and integrated messaging. It also allows sellers to market to a larger audience.(3) Differences: Our platform is exclusive to BU students, fostering a sense of trust and community. Additionally, we emphasize sustainability by promoting the reuse of items within the university.

**Craigslist:** (1) Target Audience: Open to anyone, with categories ranging from jobs to housing to items for sale. (2) Key Features: A minimalistic user interface with text-based listings, location-based search, and simple buyer-seller communication. (3) Differences: Craigslist has a broad audience, but lacks the tailored, university-specific focus and a more modern, user-friendly interface. Our platform is specifically designed for BU students, with features like verified student accounts to enhance trust.

**OfferUp:** (1) Target Audience: General public, with a focus on local transactions. (2) Key Features: Mobile-friendly app, location-based item search, and built-in messaging for easy buyer-seller communication. (3) Differences: OfferUp is more polished but lacks the exclusive community focus. Our platform provides a safer and more trusted environment as all users are verified BU students.

Overall, while these platforms cater to a wider audience, our system's advantage lies in creating a secure, eco-conscious, and community-driven marketplace that addresses the specific needs of university students.

## Architecture


## Requirements
 - Essential Features
    - **Login Page:** As a student role, Login page with phone number and password: Includes registration and admin login functionality.
    - **Register Page:** Register your account with a nickname, phone number, and password. Users should be prompted to re-enter the password for confirmation before submitting the registration.
    - **Administration Login:** An admin role is required. The login page should support login with phone number and password, and include both user registration and admin login functionality.
    - **Admin page:** The admin page should feature an item management subpage that displays both online and offline items with details like name, time, user, and an option to take items offline. It should also include order management, listing all market orders with full order details. Additionally, user management should list all platform users, with an option to ban users.
    - **Index Page:** The BU Second-Hand Market index page should include a search bar for users to search items by name, description, and more. The main section will display a list of items with pictures, name, description, price, location, and category. Additionally, it should feature a 'Publish Item' button, an announcement section, and a message center where users can view all messages from buyers or sellers.
    - **Publish Item:** The publish item page should include fields for title, description, category, location, price, and a picture upload option, followed by a submit button.
    - **Item Details:** Display all the item's information, including the picture, seller, title, and description, along with 'Buy Now' and 'Add to Cart' buttons. Users can also leave a message for the seller.
    - **Buy and Pay:** The buy and pay page appears after a user selects an item. It displays the item's details, allows the user to choose their address, and provides payment options. The user can either cancel the purchase or proceed with payment.
 - Desirable Features (the nice features that you really want to have too):
    - **Scrolling Recommendation:** The platform collects user clicks, messages, page views (PV), and unique visits (UV) to develop a recommendation algorithm that suggests items best suited to each user.
    - **Second-hand Assistance:** Utilizing the latest LLM technology, such as LLaMA, to offer an interactive chat feature that helps users quickly find items they are most interested in.
 - Optional Features (additional cool features that you want to have if there is time):

## Compile and Docker Image


## Testing


## Deployment


## Team Members
| Name   | Email | Role |
| :---------------- | :------ | :---- |
|Yingtong Zhou |cutejiu@bu.edu |  Team Leader|
|Yueyihan Qi |qyyh@bu.edu | Security Leader|
|Srujana N | sruju333@bu.edu| QA Leader|
|Ang Li |la1993@bu.edu | Design and Implementation Leader|
|Allen Chen |chhchen@bu.edu|Configuration Leader|
|Yuanbin Man |ybinman@bu.edu |Requirement Leader|