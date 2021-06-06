<template>
  <div
    class="py-3 flex flex-wrap md:flex-nowrap w-100 bg-blue-200 border-2 rounded-sm border-blue-300 my-1 px-2 flex-row font-semibold justify-between">
    <div class="md:w-24 md:mb-0 mb-6 flex-shrink-0 flex flex-col mr-4">
      <img :src="require(`@/assets/icons/${set_icon(file_information.fileFormat)}`)">
    </div>
    <div class="md:flex-grow">
      <div>{{file_information.fileName}}</div>
      <span class="text-gray-600 text-xs">{{file_information.upload_directory}}</span>
    </div>
    <div class="md:flex-grow">
      {{format_date(file_information.uploadDate)}}
    </div>
        <div class="md:flex-grow text-right">
      {{format_size(file_information.fileSize)}}
    </div>
  </div>
</template>

<script>
  export default {
    name: 'UploadedFileInfo',
    props: {
      file_information: {
        typeof: Object,
        require: true
      }
    },
    methods: {
      // делаем простенький метод для подстановки разных иконок для различных MIME-типов наших файлов
      set_icon(format) {
        if (format == "application/pdf")
          format = "pdf.png"
        else if (format == "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
          format = "word.png"
        else if (format == "image/jpeg" || format == "image/png")
          format = "image.png"
        else
          format = "base.png"
        return format
      },
      // форматирую дату к удобному виду
      format_date(date) {
        // чтобы обратиться к методам даты, необходимо создать объект класса Date
        date = new Date(date)
        // убираем AM PM (12 часовой формат)
        return date.toLocaleString('ru-RU', {
          hour12: false
        })
      },
      format_size(size){
        return Math.round(size/1000,-2)+'Кб';
      }
    }
  }
</script>

<style scoped>
</style>