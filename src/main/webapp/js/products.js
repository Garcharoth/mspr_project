const app = new Vue({
    el: '#app',
    data() { //le modèle de données

        return {
            products: [],
            create: false,
            update:false,
            newProduct: {},
            changeProduct: {}
        }
    },
    mounted() { // Ce qui est affiché au chargement de la page
        axios.get("/products")
            .then(response => {
                this.products = response.data.data;
            });
    },

    methods: {
        debug(truc){
            console.log(truc)
        },

        showById(product){
            axios.get("/product", product)

        },

        addProduct(){
            axios.post("/products", this.newProduct)
                .then(response => {
                    if(response.data.success){
                        this.newProduct = {};
                        this.create = false;
                        axios.get("/products")
                            .then(response => {
                                this.products = response.data.data;
                            })
                    }
                })
        },
        deleteProduct(product){
            axios.post("/delete_products", product)
                .then(response => {
                    if(response.data.success){
                        axios.get("/products")
                            .then(response => {
                                this.products = response.data.data;
                            })
                    }
                })
        },
        updateProduct(){
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