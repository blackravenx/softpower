<template>
  <div class="mx-auto w-100">
    <div class="mx-auto p-5 bg-indigo-600 text-center text-white font-bold">
      <h1>Чинасил Максат</h1>
    </div>
    <div class="mx-auto p-5 bg-indigo-500 flex justify-around">

      <div class="flex-wrap">
        <div v-if="message && message.length && success" class="w-100 text-xl p-2 my-2 rounded-lg ring-2 ring-gray-100"
          :class="success ? 'bg-green-300' : 'bg-red-300'">{{message}}</div>
        <label for="files" class="m-3">Выберите файлы</label>
        <input type="file" id="files" ref="files" multiple @change="add_files()">
        <button @click="upload_files()" class="bg-green-500 px-3 py-2 font-medium my-2">Отправить</button>
      </div>

    </div>
    <div class="mx-auto bg-indigo-400 rounded-sm border-t-2 border-indigo-200 sm:mb-2 lg:mb-10">
      
      <div class="container mx-auto">
        <p class="text-center">{{count}} файлов</p>
        <div
          class="flex flex-wrap md:flex-nowrap w-100 px-2 flex-row font-bold text-sm lg:text-lg justify-between">
          
          <div class="md:w-24 md:mb-0 mb-6 flex-shrink-0 flex flex-col mr-4">
            <p>Иконка</p>
            
          </div>
          <div class="md:flex-grow">
            <p>Название файла</p>
          </div>
          <div class="md:flex-grow">
            <p>Дата загрузки</p>            
          </div>
          <div class="md:flex-grow text-right">
            <p>Размер</p>
          </div>
        </div>
      </div>

    </div>

  </div>
  <div class="container mx-auto" v-for="file in uploaded_files_info" :key="file.id">
    <uploaded-file-info :file_information="file" />
  </div>
</template>

<script>
  import UploadedFileInfo from './components/UploadedFileInfo.vue'
  import axios from 'axios'
  export default {
    name: 'App',
    components: {
      UploadedFileInfo
    },
    data() {
      return {
        uploaded_files_info: typeof (Object),
        files: typeof (Array),
        message: typeof (String),
        success:''
      }
    },
    created() {
      this.get_files()
    },
    computed: {
      count() {
        return Object.keys(this.uploaded_files_info).length
      }
    },
    methods: {
      get_files() {
        axios.get("http://localhost:8085/api/files").
        then(response => (this.uploaded_files_info = response.data)).
        catch(e => {
          console.log(e)
        })
      },
      upload_files() {
        let UploadingFiles = new FormData();
        this.files.forEach(file => {
          UploadingFiles.append('files', file);
        });
        axios.post('http://localhost:8085/api/files/save', UploadingFiles, {
            headers: {
              'Content-Type': 'multipart/form-data'
            },
          }).then(response => (this.message = response.data, this.success = true))
          .catch(e => this.message = e, this.success = false);
        // очищаем массив с файлами, и вызываем метод загрузки данных с бэка
        this.files = '';
        this.get_files();
      },
      add_files() {
        this.files = this.$refs.files.files
      }
    }
  }
</script>

<style>
</style>