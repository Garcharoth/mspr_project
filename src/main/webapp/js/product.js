const app = new Vue({
    el: '#app',
    data() { //le modèle de données

        return {
            product: product,
            create: false,
            update:false,
        }
    },
    mounted() { // Ce qui est affiché au chargement de la page
        this.loadProduct();
    },

    methods: {
        debug(truc){
            console.log(truc)
        },

        loadProduct() {
            let productId = localStorage.getItem('product.id');
            axios.get('/product?productId=' + productIdId)
                .then(response => {
                    this.product = response.data.data;
                })
        },
        updateProduct(product){
            // this.changeProduct("id") = truc;
            // let productId = localStorage.getItem("product.id");
            axios.post("/update_products", this.changeProduct)
                .then(response => {
                    if(response.data.success){
                        this.changeProduct = {};
                        this.update = false;
                        axios.get("/products")
                            .then(response => {
                                this.products = response.data.data;
                            })
                    }
                })
        }
    }
});