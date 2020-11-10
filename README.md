**Supermarket Pricing**
 
The problem domain is something seemingly simple: pricing goods at supermarkets.
 
Some things in supermarkets have simple prices: this can of beans costs $0.65. Other things have more complex prices. For example:
*   three for a dollar (so what’s the price if I buy 4, or 5?)
*   $1.99/pound (so what does 4 ounces cost?)
*   buy two, get one free (so does the third item have a price?)

 
**Objectives of the Kata**
To some extent, this is just a fun little problem. But underneath the covers, it’s a stealth exercise in decoupling. The challenge description doesn’t mention the format of the pricing rules. How can these be specified in such a way that the checkout doesn’t know about particular items and their pricing strategies? How can we make the design flexible enough so that we can add new styles of pricing rule in the future?
